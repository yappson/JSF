package com.emp.manage.QualifactionBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.emp.manage.Repo.QualifactionRepo;
import com.emp.manage.enitity.Qualification;

@ManagedBean
@ViewScoped
public class GridBean {
	
	@Autowired
	private QualifactionRepo qualifactionRepo;

	private List<Qualification> gridItems = new ArrayList<>();

    public void addGridItem() {
    
        gridItems.add(new Qualification());
    }

    public void removeGridItem(Qualification item) {
        gridItems.remove(item);
    }

    public void submit() {
        for (Qualification qualification : gridItems) {
            // Perform validation or additional processing if needed
            
        }
    }

	public List<Qualification> getGridItems() {
		return gridItems;
	}

	public void setGridItems(List<Qualification> gridItems) {
		this.gridItems = gridItems;
	}
    
    
}