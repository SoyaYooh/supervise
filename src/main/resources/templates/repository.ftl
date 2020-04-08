package ${entity.packageName}.repository;
import ${entity.packageName}.dto.${entity.table.entityName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author ${entity.author}
 * @date ${entity.date}
 */
@Repository
public interface ${entity.table.entityName}Repository extends JpaRepository<${entity.table.entityName},Long>{
	@Query(value = "select t from ${entity.table.entityName} t where  t.id = ?1")
    ${entity.table.entityName} findById(String id);
    @Query(value = "delete from  ${entity.table.entityName}t where  t.id = ?1")
	void deleteById(String id);
}
