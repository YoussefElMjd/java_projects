package model;

import config.ConfigManager;
import dto.SimulationDto;
import exception.RepositoryException;
import repository.SimulationRepository;
import view.View;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model extends Observable {

    List<Runnable> runnables;
    ExecutorService pool;
    SimulationRepository repository;

    public Model() {
        try {
            ConfigManager.getInstance().load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dbUrl = ConfigManager.getInstance().getProperties("db.url");
        System.out.println("Base de données stockée = " + dbUrl);
        this.runnables = new ArrayList<>();
        try {
            this.repository = new SimulationRepository();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        notifyObs();
    }

    public void initialize(LocalDateTime begin, int nbThread, String sort, Configuration configurationChoice, View view) {
        SimulationDto dto = new SimulationDto(begin, sort, configurationChoice.getValue());
        try {
            repository.insert(dto);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        this.runnables.clear();
        int nbValue = configurationChoice.getValue();
        int incrementby = nbValue / 10;
        int startWith = 0;
        if (sort.equals("BUBBLE")) {
            for (int i = 0; i <= 10; i++) {
                BubbleRunnable bubble = new BubbleRunnable(createArray(startWith));
                bubble.subscribe(view);
                runnables.add(bubble);
                startWith += incrementby;
            }
        } else {
            for (int i = 0; i <= 10; i++) {
                MergeRunnable merge = new MergeRunnable(createArray(startWith));
                merge.subscribe(view);
                runnables.add(merge);
                startWith += incrementby;
            }
        }

    }

    public void start(int nbThread) {
        pool = Executors.newFixedThreadPool(nbThread);
        for (Runnable runnable : runnables) {
            pool.execute(runnable);
        }
        pool.shutdown();
    }

    private int[] createArray(int startWith) {
        int[] arr = new int[startWith];

        for (int i = 0; i < startWith; i++) {
            arr[i] = (int) (Math.random() * 1000000 + 1);
        }
        return arr;
    }

    public List<SimulationDto> getAllSimulations() {
        List<SimulationDto> allSimulations = null;
        try {
            allSimulations = repository.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return allSimulations;
    }
}
