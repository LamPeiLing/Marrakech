package comp1110.ass2.testdata;

import comp1110.ass2.Scores;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScoresTest {

    @Test
    public void testSetAndGetDirhamScore() {
        // Test the methods for setting and getting coin scores

        // Create a Scores object
        Scores scores = new Scores();
        // Set the coin score to 10
        scores.setDirhamScore(10);
        // Verify that the acquired coin score is 10
        assertEquals(10, scores.getDirhamScore());
    }

    @Test
    public void testSetAndGetRugScore() {
        // Test the methods for setting and getting rug scores

        // Create a Scores object
        Scores scores = new Scores();
        // Set the rug score to 5
        scores.setRugScore(5);
        // Verify that the obtained rug score is 5
        assertEquals(5, scores.getRugScore());
    }

    @Test
    public void testUpdateDirhamScore() {
        // Test the method for updating coin scores

        // Create a Scores object
        Scores scores = new Scores();
        // Set the coin score to 20
        scores.setDirhamScore(20);

        // Set the coin score to 20
        scores.updateDirhamScore(5, true); // 增加5分
        // Verify that the acquired coin score is 25
        assertEquals(25, scores.getDirhamScore());

        // Testing coin reduction scores
        scores.updateDirhamScore(8, false); // 减少8分
        // Verify that the acquired coin score is 17
        assertEquals(17, scores.getDirhamScore());
    }

    @Test
    public void testUpdateRugScore() {
        // Test the method for updating rug scores

        // Create a Scores object
        Scores scores = new Scores();
        // Set the rug score to 10
        scores.setRugScore(10);

        // Test Placement rug Score
        scores.updateRugScore(false); // 放置地毯，得分+2
        // Verify that the obtained rug score is 12
        assertEquals(12, scores.getRugScore());

        // Test rug overlapped by other carpets
        scores.updateRugScore(true); // 地毯被重叠，得分-1
        // Verify that the obtained rug score is 11
        assertEquals(11, scores.getRugScore());
    }

    @Test
    public void testGetTotalScore() {
        // 测试获取总分数的方法

        // 创建一个Scores对象
        Scores scores = new Scores();
        // 设置硬币分数为15
        scores.setDirhamScore(15);
        // 设置地毯分数为8
        scores.setRugScore(8);

        // 验证总分计算是否正确，期望总分为23
        assertEquals(23, scores.getTotalScore());
    }
}
