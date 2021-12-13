package br.dev.mhc.authentication.services.interfaces;

import java.util.List;

public interface CrudInterface<T, ID> {
	
	public T insert(T obj);

	public T update(T obj);

	public void delete(ID id);

	public T findById(ID id);

	public List<T> findAll();

}
