package micronaut.example.service;

import micronaut.example.controller.CreateMovieRequest;
import micronaut.example.controller.SearchMovieResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import javax.inject.Singleton;

@Singleton
public class MovieMapperImpl implements MovieMapper {

    @Override
    public Movie toMovie(CreateMovieRequest createMovieRequest) {
        return new Movie(createMovieRequest.getImdb(), createMovieRequest.getTitle());
    }

    @Override
    public SearchMovieResponse toSearchMovieDto(SearchHits searchHits, TimeValue took) {
        SearchMovieResponse searchMovieDto = new SearchMovieResponse();
        for (SearchHit searchHit : searchHits.getHits()) {
            SearchMovieResponse.Hit hit = new SearchMovieResponse.Hit(
                    searchHit.getIndex(),
                    searchHit.getId(),
                    searchHit.getScore(),
                    searchHit.getSourceAsString()
            );
            searchMovieDto.getHits().add(hit);
        }
        searchMovieDto.setTook(took.toString());
        return searchMovieDto;
    }
}
