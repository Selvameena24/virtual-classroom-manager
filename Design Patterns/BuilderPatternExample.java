// House.java
class House {
    private String foundation;
    private String structure;
    private String roof;
    private boolean isPainted;
    private boolean hasGarden;

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setPainted(boolean isPainted) {
        this.isPainted = isPainted;
    }

    public void setGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    @Override
    public String toString() {
        return "House [foundation=" + foundation + ", structure=" + structure + ", roof=" + roof + 
               ", isPainted=" + isPainted + ", hasGarden=" + hasGarden + "]";
    }
}
// HouseBuilder.java
interface HouseBuilder {
    void buildFoundation();
    void buildStructure();
    void buildRoof();
    void paintHouse();
    void buildGarden();
    House getHouse();
}
// ConcreteHouseBuilder.java
class ConcreteHouseBuilder implements HouseBuilder {
    private House house;

    public ConcreteHouseBuilder() {
        this.house = new House();
    }

    @Override
    public void buildFoundation() {
        house.setFoundation("Concrete, brick, and stone");
        System.out.println("Foundation complete");
    }

    @Override
    public void buildStructure() {
        house.setStructure("Wood and brick");
        System.out.println("Structure complete");
    }

    @Override
    public void buildRoof() {
        house.setRoof("Metal roof");
        System.out.println("Roof complete");
    }

    @Override
    public void paintHouse() {
        house.setPainted(true);
        System.out.println("House painted");
    }

    @Override
    public void buildGarden() {
        house.setGarden(true);
        System.out.println("Garden complete");
    }

    @Override
    public House getHouse() {
        return this.house;
    }
}
// Director.java
class Director {
    private HouseBuilder houseBuilder;

    public Director(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public void constructHouse() {
        houseBuilder.buildFoundation();
        houseBuilder.buildStructure();
        houseBuilder.buildRoof();
        houseBuilder.paintHouse();
        houseBuilder.buildGarden();
    }

    public House getHouse() {
        return this.houseBuilder.getHouse();
    }
}
// BuilderPatternExample.java
public class BuilderPatternExample {
    public static void main(String[] args) {
        HouseBuilder houseBuilder = new ConcreteHouseBuilder();
        Director director = new Director(houseBuilder);
        
        director.constructHouse();
        House house = director.getHouse();
        
        System.out.println("House is: " + house);
    }
}
