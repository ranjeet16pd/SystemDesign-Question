package StrategyDesignPattern;

interface Talkable {
    public void talk();
}

interface Walkable {
    public void walk();
}

interface Flyable {
    public void fly();
}

class NormalTalk implements Talkable {

    @Override
    public void talk() {
        System.out.println("This robot is talking in normal Way");
    }
}

class NoTalk implements Talkable {
    @Override
    public void talk() {
        System.out.println("This robot do not talk");
    }
}

class NormalWalk implements Walkable {
    @Override
    public void walk() {
        System.out.println("This robot is no Walking in normal Way");
    }
}

class NoWalk implements Walkable {
    @Override
    public void walk() {
        System.out.println("This robot do not  Walk ");
    }
}

class NoFly implements Flyable {
    @Override
    public void fly() {
        System.out.println("This robot do not fly");
    }
}

class NormalFly implements Flyable {
    @Override
    public void fly() {
        System.out.println("This robot Flying in normal Way");
    }
}

abstract class RobotClient {

    protected Talkable talkable;
    protected Walkable walkable;
    protected Flyable flyable;


    RobotClient(Talkable talk, Walkable walk, Flyable fly) {
        this.talkable = talk;
        this.walkable = walk;
        this.flyable = fly;
    }

    public void talk() {
        talkable.talk();
    }

    public void walk() {
        walkable.walk();
    }

    public void fly() {
        flyable.fly();
    }

    public abstract void projection();

}

class CompanionRobot extends RobotClient {

    CompanionRobot(Talkable talk, Walkable walk, Flyable fly) {
        super(talk, walk, fly);
    }

    @Override
    public void projection() {
        System.out.println("CompanionRobot projection is displayed");
    }
}

class WorkerRobot extends RobotClient {
    WorkerRobot(Talkable talk, Walkable walk, Flyable fly) {
        super(talk, walk, fly);
    }

    @Override
    public void projection() {
        System.out.println("WorkerRobot projection is displayed");
    }
}

public class Strategy {

    public static void main(String[] args) {

        RobotClient robotClient = new CompanionRobot(new NormalTalk(), new NormalWalk(), new NormalFly());
        robotClient.talk();
        robotClient.walk();
        robotClient.fly();
        robotClient.projection();

        System.out.println("---------------------------------------------------");

        RobotClient robotClient2 = new WorkerRobot(new NoTalk(), new NoWalk(), new NoFly());
        robotClient2.talk();
        robotClient2.walk();
        robotClient2.fly();
        robotClient2.projection();

    }
}
