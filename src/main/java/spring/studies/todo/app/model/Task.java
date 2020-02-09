package spring.studies.todo.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private boolean complete;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete){
        this.complete = complete;
    }
}
