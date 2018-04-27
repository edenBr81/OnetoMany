package edu.eden.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

    @RequestMapping("/")
    public String index(Model model){


        Artist artist=new Artist();
        artist.setName("Nesta Robert Marley");
        artist.setGenre("Reggae Music");
        artist.setStagename("Bob Marley");

        Set<Song>songs = new HashSet<Song>();
        Song song= new Song();
        song.setTitle("Survival");
        song.setYear("1979");
        song.setArtist(artist);
        songs.add(song);

        song =new Song();
        song.setTitle("Redemption");
        song.setYear("1980");
        song.setArtist(artist);
        songs.add(song);

        artist.setSongs(songs);

        artistRepository.save(artist);


        artist=new Artist();
        artist.setName("Eden Marley");
        artist.setGenre("Rap Music");
        artist.setStagename("Mesky");

         songs = new HashSet<Song>();
         song= new Song();
         song.setTitle("alber endamora");
         song.setYear("2017");
         song.setArtist(artist);
         songs.add(song);

        song =new Song();
        song.setTitle("arb let");
        song.setYear("2018");
        song.setArtist(artist);
        songs.add(song);


        artist.setSongs(songs);

        artistRepository.save(artist);

        model.addAttribute("artist", artistRepository.findAll());
        model.addAttribute("menuoption","Home");
        return "index";

    }

    @RequestMapping("/showartists")
    public String showArtist(Model model){
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("menuoption","Artists");
        return "showartists";

    }
    @RequestMapping("/showsongs")
    public String showSong(Model model){
        model.addAttribute("songs", songRepository.findAll());
        model.addAttribute("menuoption","Songs");
        return "showsongs";
    }

    @GetMapping("/addartist")
    public String addArtist(Model model){
        model.addAttribute("artist" , new Artist());
        model.addAttribute("menuoption",  "Add an Artist");
        return "addartistform";
    }

   @PostMapping("/processartist")
    public String processForm(@Valid Artist artist, BindingResult result ){
      if(result.hasErrors()){
          return "addartistform";
      }

      //setting default image if image is not available
       if(artist.getImage()==null || artist.getImage().isEmpty()){
           artist.setImage("https://i.ytimg.com/vi/gODh1nsHlPg/hqdefault.jpg");
       }
      artistRepository.save(artist);
      return "redirect:/";
   }

   @GetMapping("/addsong")
    public String addSong(Model model){
        model.addAttribute("song", new Song());
        model.addAttribute("menuoption", "Add a Song");
        return "addsongform";
        }

    @PostMapping("/process")
    public String processForm(@Valid Song song, BindingResult result ){
        if(result.hasErrors()){
            return "addsongform";
        }

        songRepository.save(song);
        return "redirect:/";
    }


}
