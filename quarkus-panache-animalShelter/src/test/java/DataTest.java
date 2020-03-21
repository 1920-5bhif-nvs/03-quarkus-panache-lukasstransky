import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
public class DataTest {

    @Inject
    DataSource dataSource;

    @Test
    public void test01_data_of_table_animalShelter_correct(){
        Table animalShelter = new Table(this.dataSource, "animalshelter");
        assertThat(animalShelter)
                .column("town")
                .hasValues("Leonding", "Linz", "Wien");
        assertThat(animalShelter).hasNumberOfRows(3);
    }

    @Test
    public void test02_data_of_table_cage_correct(){
        Table cage = new Table(this.dataSource, "cage");
        assertThat(cage)
                .column("cagecolumn")
                .hasValues(10, 2, 14, 9, 21);
        assertThat(cage)
                .column("animalshelter_id")
                .hasValues(1, 1, 2, 3, 3);
        assertThat(cage).hasNumberOfRows(5);
    }

    @Test
    public void test03_data_of_table_pet_correct(){
        Table pet = new Table(this.dataSource, "pet");
        assertThat(pet)
                .column("dtype")
                .hasValues("Dog", "Dog", "Dog", "Cat", "Cat", "Dog", "Cat");
        assertThat(pet)
                .column("name")
                .hasValues("Luna", "Jessy", "Rocky", "Lilly", "Timmy", "Axel", "Lorenzo");
        assertThat(pet).hasNumberOfRows(7);
    }

    @Test
    public void test04_data_of_table_dog_correct(){
        Table dog = new Table(this.dataSource, "dog");
        assertThat(dog).hasNumberOfRows(4);
    }

    @Test
    public void test05_data_of_table_cat_correct(){
        Table cat = new Table(this.dataSource, "cat");
        assertThat(cat).hasNumberOfRows(3);
    }

    @Test
    public void test06_requests(){
        Request request1 = new Request(this.dataSource,
                "select street, town from animalshelter where id = 2 or id = 3");
        assertThat(request1).row(0).value(1).equals("Linz");
        assertThat(request1).column(1).value(0).equals("Linz");
    }

    @Test
    public void test07_changes(){
        Changes changes = new Changes(this.dataSource);
        changes.setStartPointNow();

        try{
            PreparedStatement statement = this.dataSource.getConnection().prepareStatement(
                    "insert into animalshelter (post_code, street, town) values (?, ?, ?)");
            statement.setInt(1,1234);
            statement.setString(2,"Die Straße");
            statement.setString(3, "Ganghausen");
            statement.executeUpdate();

            statement = this.dataSource.getConnection().prepareStatement(
                    "delete from animalshelter where post_code = 1234 and street like 'Die Straße' and town like 'Ganghausen'"
            );
            statement.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Fehler: " + ex.getMessage());
        }
        changes.setEndPointNow();
        assertThat(changes).hasNumberOfChanges(2);


    }
}
