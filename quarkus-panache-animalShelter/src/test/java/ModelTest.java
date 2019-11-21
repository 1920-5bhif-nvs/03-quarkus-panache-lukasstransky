import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import javax.sql.DataSource;
import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
public class ModelTest {

    @Inject
    DataSource dataSource;

    @Test
    public void test01_table_cage_created(){
        Table cage = new Table(this.dataSource, "cage");
        assertThat(cage).column("id").isNumber(true);
        assertThat(cage).column("cagerow").isNumber(true);
        assertThat(cage).column("cagecolumn").isNumber(true);
        assertThat(cage).column("animalshelter_id").isNumber(true);
    }

    @Test
    public void test02_table_animalShelter_created(){
        Table animalShelter = new Table(this.dataSource, "animalshelter");
        assertThat(animalShelter).column("id").isNumber(true);
        assertThat(animalShelter).column("post_code").isNumber(true);
        assertThat(animalShelter).column("street").isText(true);
        assertThat(animalShelter).column("town").isText(true);
    }

    @Test
    public void test03_table_pet_created(){
        Table pet = new Table(this.dataSource, "pet");
        assertThat(pet).column("dtype").isText(true);
        assertThat(pet).column("id").isNumber(true);
        assertThat(pet).column("age").isNumber(true);
        assertThat(pet).column("breed").isText(true);
        assertThat(pet).column("name").isText(true);
        assertThat(pet).column("price").isNumber(true);
        assertThat(pet).column("weight").isNumber(true);
        assertThat(pet).column("cage_id").isNumber(true);
    }

    @Test
    public void test04_table_dog_created(){
        Table dog = new Table(this.dataSource, "dog");
        assertThat(dog).column("id").isNumber(true);
    }

    @Test
    public void test05_table_cat_created(){
        Table cat = new Table(this.dataSource, "cat");
        assertThat(cat).column("id").isNumber(true);
    }
}
