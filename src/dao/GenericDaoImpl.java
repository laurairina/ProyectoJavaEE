package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//esto implementa la interfaz del GenericDao
public class GenericDaoImpl<T, K> implements GenericDao<T, K> {
	@PersistenceContext // Spring manejara las conexiones 
	protected EntityManager entityManager;
	private Class<T> clase; 
		
	@SuppressWarnings("unchecked")
	// see: http://stackoverflow.com/questions/182636/how-to-determine-the-class-of-a-generic-type
	
	public GenericDaoImpl() {
		this.clase =  (Class<T>) ((ParameterizedType)
		        getClass()
		        .getGenericSuperclass())
		        .getActualTypeArguments()[0];
	}
	
	@Override
	public void agregar(T obj) {
		entityManager.persist(obj); 
	}

	@Override
	public void modificar(T obj) {
		entityManager.merge(obj);
	}

	@Override
	public void eliminar(K id) {
		T o = obtener(id);
		entityManager.remove(o);
	}

	@Override
	public T obtener(K id) {
		return entityManager.find(clase, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> obtenerTodos() {
		return entityManager
				.createNamedQuery(clase.getSimpleName() + ".findAll")
				.getResultList();
	}


	
}
