package com.example.task.Repository;
import com.example.task.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query(nativeQuery = true, value = "select *from task where user_id =:userId")
    List<Task> findByTaskId(@Param("userId") Long userid);


    @Query(nativeQuery = true, value = "select * from task join user_role ur on task.user_id = ur.user_id where ur.user_id = :userId ")
    List<Task> findByTaskByRole(@Param("userId") Long userid);

    @Query(nativeQuery = true, value = "select * from task where project_id = :projectId ")
    List<Task> findByProjectId(@Param("projectId") Long projectId);

    @Query(nativeQuery = true, value = "select * from task join schedule_task st on task.id = st.task_id where task_id =:taskId ")
    List<Task> findTaskByTaskId(@Param("taskId") Long taskId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM task t where t.project_id =:projectId")
    void deleteProjectId(@Param("projectId") Long projectId);
}
