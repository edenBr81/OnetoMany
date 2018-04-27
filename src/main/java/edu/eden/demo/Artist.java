package edu.eden.demo;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String genre;
    private String stagename;
    private String image;


    @OneToMany(mappedBy = "artist",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    public Set<Song> songs;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public String getStagename() {
        return stagename;
    }

    public void setStagename(String stagename) {
        this.stagename = stagename;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
