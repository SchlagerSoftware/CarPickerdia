package com.example.carpickerdia.viewmodels.expert;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.carpickerdia.models.Car;
import com.example.carpickerdia.repositories.InfoRepository;

import java.util.List;

public class SpecificCarViewModel extends ViewModel {
    InfoRepository infoRepository;

    public SpecificCarViewModel(){
        infoRepository = InfoRepository.getInstance();
    }

    public LiveData<Car> getCurrentCarByModel() {
        return infoRepository.getCurrentCarByModel();
    }
}
