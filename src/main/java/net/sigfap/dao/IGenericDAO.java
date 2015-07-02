package net.sigfap.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface IGenericDAO<Entity, ID extends Serializable> 
{ 
    void deleteById(ID id); 
    
    /**
     * Get the Class of the entity.
     *
     * @return the class
     */
    Class<Entity> getEntityClass();

    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity
     */
    Entity findById(final ID id, boolean lock);

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    List<Entity> findAll();

    /**
     * Find entities based on an example.
     *
     * @param exampleInstance the example
     * @return the list of entities
     */
    List<Entity> findByExample(final Entity exampleInstance);

    

    /**
     * Count all entities.
     *
     * @return the number of entities
     */
    int countAll();

    /**
     * Count entities based on an example.
     *
     * @param exampleInstance the search criteria
     * @return the number of entities
     */
    int countByExample(final Entity exampleInstance);

    
    /**
     * save an entity. This can be either a INSERT or UPDATE in the database.
     * 
     * @param entity the entity to save
     * 
     * @return the saved entity
     */
    Entity persist(final Entity entity);
    
    Entity update(final Entity entity);
    

    /**
     * delete an entity from the database.
     * 
     * @param entity the entity to delete
     */
    void delete(final Entity entity);
    
    List<Entity> findByCriteria(Criterion... criterion);
    
    List<Entity> findByCriteriaPageByPage(int firstResult, int maxResults, Criterion... criterion);
}
