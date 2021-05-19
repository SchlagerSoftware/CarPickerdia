package com.example.carpickerdia.viewmodels.expert;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.carpickerdia.repositories.InfoRepository;

import java.util.List;

public class AllModelsViewModel extends ViewModel {
    InfoRepository infoRepository;

    public AllModelsViewModel() {
        this.infoRepository = InfoRepository.getInstance();
    }

    public LiveData<List<String>> getAllModels() {
        return infoRepository.getAllModels();
    }

    public void getAllCarModels(String make) {
        infoRepository.getAllCarModels(make);
    }
}