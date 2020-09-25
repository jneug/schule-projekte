
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DecisionTreeTest {

    private static final String[][] known_data = {
        {"sonnig", "heiß", "hoch", "schwach", "ja"},
        {"sonnig", "heiß", "hoch", "stark", "nein"},
        {"bewölkt", "heiß", "hoch", "schwach", "ja"},
        {"regnerisch", "mild", "hoch", "schwach", "nein"},
        {"regnerisch", "kalt", "normal", "schwach", "ja"},
        {"regnerisch", "kalt", "normal", "stark", "ja"},
        {"bewölkt", "mild", "hoch", "stark", "ja"},
        {"sonnig", "mild", "hoch", "schwach", "ja"},
        {"sonnig", "kalt", "normal", "schwach", "ja"},
        {"regnerisch", "mild", "normal", "schwach", "ja"},
        {"sonnig", "mild", "normal", "stark", "nein"},
        {"bewölkt", "heiß", "normal", "schwach", "ja"},
        {"bewölkt", "kalt", "normal", "stark", "ja"},
        {"regnerisch", "mild", "hoch", "stark", "nein"}
    };

    private static final String[][] unknown_data = {
        {"regnerisch", "heiß", "hoch", "schwach", "nein"},
        {"sonnig", "kalt", "hoch", "stark", "nein"}
    };

    private DecisionTreeBuilder tree;

    private Dataset[] known_datasets, unknown_datasets;

    @Before
    public void setUp() {
        known_datasets = new Dataset[known_data.length];
        for( int i = 0; i < known_data.length; i++ ) {
            known_datasets[i] = new Dataset();
            known_datasets[i].set("vorhersage", known_data[i][0]);
            known_datasets[i].set("temperatur", known_data[i][1]);
            known_datasets[i].set("feuchtigkeit", known_data[i][2]);
            known_datasets[i].set("wind", known_data[i][3]);
        }

        unknown_datasets = new Dataset[unknown_data.length];
        for( int i = 0; i < unknown_data.length; i++ ) {
            unknown_datasets[i] = new Dataset();
            unknown_datasets[i].set("vorhersage", unknown_data[i][0]);
            unknown_datasets[i].set("temperatur", unknown_data[i][1]);
            unknown_datasets[i].set("feuchtigkeit", unknown_data[i][2]);
            unknown_datasets[i].set("wind", unknown_data[i][3]);
        }

        tree = new DecisionTreeBuilder();
    }

    @Test
    public void testKnownData() {
        for( int i = 0; i < known_datasets.length; i++ ) {
            String result = tree.testDataset(known_datasets[i]);
            assertEquals(known_data[i][4], result);
        }
    }

    @Test
    public void testUnknownData() {
        for( int i = 0; i < unknown_datasets.length; i++ ) {
            String result = tree.testDataset(unknown_datasets[i]);
            assertEquals(unknown_data[i][4], result);
        }
    }

}
