package com.mycompany.methotels.pages;

import com.mycompany.methotels.components.GenericEditor;
import com.mycompany.methotels.dao.HotelCityDao;
import com.mycompany.methotels.entities.City;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

public class AddCity {

    @Property
    @Persist
    private City city;
    @Property
    private City selectedcity;
    @Inject
    private HotelCityDao cityDao;
    @Property
    private List<City> cityList;

    void onActivate() {
    }

    @PageLoaded
    void onPageLoad() {
        if (cityList == null) {
            cityList = new ArrayList<City>();
        }
        cityList = cityDao.getAllCitiesList();
    }

    @CommitAfter
    Object onSuccess() {
        cityDao.addCity(city);
        return this;
    }

    @CommitAfter
    Object onActionFromDelete(int id) {
        cityDao.addOrUpdateCity(city);
        city = new City();
        return this;
    }

    @CommitAfter
    Object onActionFromEdit(City city) {
        this.city = city;
        return this;
    }
}
