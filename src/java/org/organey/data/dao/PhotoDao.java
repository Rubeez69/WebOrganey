package org.organey.data.dao;

import java.util.List;

import org.organey.data.model.Photo;

public interface PhotoDao {
	public void insert(Photo photo);
	public void update(Photo photo);
	public void delete(int photoId);
	public Photo find(int photoId);
	public List<Photo> findAll();
}
