package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Disabled
public class IndexControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;
    @InjectMocks
    IndexController controller;
    List<Recipe> recipes;
    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        recipes = new ArrayList<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId("1");

        recipes.add(recipe);

        MockitoAnnotations.openMocks(this);

        webTestClient = WebTestClient.bindToController(controller).build();

    }

    @Test
    public void testMockMVC() {
        when(recipeService.getRecipes()).thenReturn(Flux.empty());

        webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    @Test
    public void getIndexPage() {

        when(recipeService.getRecipes()).thenReturn(Flux.fromIterable(recipes));

        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        //when
        String viewName = controller.getIndexPage(model);


        //then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        List<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

}