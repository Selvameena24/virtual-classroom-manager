// Subsystem classes
class DVDPlayer {
    public void on() {
        System.out.println("DVD Player is on");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    public void off() {
        System.out.println("DVD Player is off");
    }
}
class Projector {
    public void on() {
        System.out.println("Projector is on");
    }

    public void off() {
        System.out.println("Projector is off");
    }

    public void wideScreenMode() {
        System.out.println("Projector in widescreen mode");
    }
}
class SoundSystem {
    public void on() {
        System.out.println("Sound System is on");
    }

    public void off() {
        System.out.println("Sound System is off");
    }

    public void setVolume(int level) {
        System.out.println("Setting volume to " + level);
    }
}
class Screen {
    public void down() {
        System.out.println("Screen down");
    }

    public void up() {
        System.out.println("Screen up");
    }
}
// Facade class
class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;
    private Screen screen;

    public HomeTheaterFacade(DVDPlayer dvdPlayer, Projector projector, SoundSystem soundSystem, Screen screen) {
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.screen = screen;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        screen.down();
        projector.on();
        projector.wideScreenMode();
        soundSystem.on();
        soundSystem.setVolume(5);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        dvdPlayer.off();
        soundSystem.off();
        projector.off();
        screen.up();
    }
}
// Client code
public class FacadePatternExample {
    public static void main(String[] args) {
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();
        Screen screen = new Screen();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvdPlayer, projector, soundSystem, screen);

        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}

