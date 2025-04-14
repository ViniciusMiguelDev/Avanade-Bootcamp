package br.com.avanade.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.avanade.exceptions.NotFoundException;
import br.com.avanade.models.News;
import br.com.avanade.repositories.NewsRepository;
import br.com.avanade.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public News create(Long id, News news) {

        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Não existe um usuário com esse id: " + id);
        }

        news.setUser(userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        news.setId(null);
        return newsRepository.save(news);
    }

    public News read(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<News> readAll() {
        return newsRepository.findAll();
    }

    public News update(Long idUser, Long idNews, News news) {

        if (!userRepository.existsById(idUser)) {
            throw new NotFoundException("Não existe um usuário com esse id: " + idUser);
        }

        News newNews = newsRepository.findById(idNews).orElseThrow(() -> new RuntimeException("User not found"));
        newNews.setIcon(news.getIcon());
        newNews.setDescription(news.getDescription());
        newNews.setUser(news.getUser());

        return newsRepository.save(newNews);
    }

    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}
