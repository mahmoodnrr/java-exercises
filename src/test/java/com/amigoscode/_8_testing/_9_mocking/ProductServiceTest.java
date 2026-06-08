package com.amigoscode._8_testing._9_mocking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Exercise: Building and Testing a Service from Scratch
 * <p>
 * In this exercise you will define the types and service inline, then write
 * mock-based tests. This simulates the real-world workflow of designing
 * interfaces, services, and tests together.
 * <p>
 * The Product, ProductRepository, and ProductService are defined as inner
 * types below so everything is self-contained.
 */
@DisplayName("Product Service Tests")
@ExtendWith(MockitoExtension.class)
class
ProductServiceTest {

    // --- Inner types for the exercise ---

    /**
     * Represents a product in the store.
     */
    record Product(String id, String name, double price) {
    }

    /**
     * Repository interface for Product persistence.
     */
    interface ProductRepository {
        void save(Product product);

        Optional<Product> findById(String id);

        List<Product> findAll();

        void deleteById(String id);

        boolean existsById(String id);
    }

    /**
     * Service class for managing products.
     */
    static class ProductService {
        private final ProductRepository repository;

        ProductService(ProductRepository repository) {
            this.repository = repository;
        }

        public Product findById(String id) {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found: " + id));
        }

        public List<Product> findAll() {
            return repository.findAll();
        }

        public void save(Product product) {
            Objects.requireNonNull(product, "Product cannot be null");
            repository.save(product);
        }

        public void delete(String id) {
            if (!repository.existsById(id)) {
                throw new RuntimeException("Cannot delete: product not found: " + id);
            }
            repository.deleteById(id);
        }
    }

    // --- Test setup ---

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    // TODO: 1 - Create a Product record and verify its fields.
    //  Create: Product laptop = new Product("P-1", "Laptop", 999.99);
    //  Assert that laptop.id() equals "P-1".
    //  Assert that laptop.name() equals "Laptop".
    //  Assert that laptop.price() equals 999.99.
    //  This is a simple sanity check, no mocking needed.

    @Test
    void canPassSanityTest_Laptop() {
        Product laptop = new Product("P-1", "Laptop", 999.99);
        assertEquals("P-1", laptop.id());
        assertEquals("Laptop", laptop.name());
        assertEquals(999.99, laptop.price());
    }


    // TODO: 2 - Mock ProductRepository and test findById returns a product.
    //  Create a product: Product phone = new Product("P-2", "Phone", 699.99);
    //  Stub: when(productRepository.findById("P-2")).thenReturn(Optional.of(phone));
    //  Call productService.findById("P-2").
    //  Assert the returned product's name is "Phone".
    //  Assert the returned product's price is 699.99.
    @Test
    void canFindPhone() {

        Product phone = new Product("P-2", "Phone", 699.99);
        when(productRepository.findById(anyString())).thenReturn(Optional.of(phone));

        Product p = productService.findById("P-2");

        assertEquals("P-2", p.id());
        assertEquals(699.99, p.price());
        assertEquals("Phone", p.name());
    }


    // TODO: 3 - Test that findById throws when product is not found.
    //  Stub: when(productRepository.findById("MISSING")).thenReturn(Optional.empty());
    //  Assert that productService.findById("MISSING") throws RuntimeException.
    //  Verify the exception message contains "not found".
    @Test
    void canThrowRuntimeException() {
        when(productRepository.findById("Missing")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.findById("Missing"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found");
    }

    // TODO: 4 - Test that save calls repository.save().
    //  Create a product.
    //  Call productService.save(product).
    //  Verify: verify(productRepository).save(product);
    //  Also verify: verify(productRepository, times(1)).save(any(Product.class));
    @Test
    void canSaveProduct() {

        Product product = new Product("P-2", "Phone", 699.99);
        productService.save(product);

        verify(productRepository).save(product);
        verify(productRepository, times(1)).save(product);
    }

    // TODO: 5 - Test that save with null throws NullPointerException.
    //  Assert that productService.save(null) throws NullPointerException.
    //  Verify that repository.save was never called:
    //  verify(productRepository, never()).save(any());
    @Test
    void canThrowNullException() {

        assertThrows(NullPointerException.class, () -> productService.save(null));

        verify(productRepository, never()).save(any(Product.class));
    }

    // TODO: 6 - Test that delete throws when product does not exist.
    //  Stub: when(productRepository.existsById("MISSING")).thenReturn(false);
    //  Assert that .delete("MISSING") throws RuntimeException.
    //  Verify: verify(productproductServiceRepository, never()).deleteById(anyString());
    @Test
    void canThrowWhenDeletingMissingProduct() {

        when(productRepository.existsById("MISSING")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> productService.delete("MISSING"));

        verify(productRepository, never()).deleteById("MISSING");
    }

    // TODO: 7 - Test that delete calls repository.deleteById for existing product.
    //  Stub: when(productRepository.existsById("P-1")).thenReturn(true);
    //  Call productService.delete("P-1").
    //  Verify: verify(productRepository).existsById("P-1");
    //  Verify: verify(productRepository).deleteById("P-1");
    @Test
    void canDeleteProduct() {

        when(productRepository.existsById("P-1")).thenReturn(true);

        productService.delete("P-1");

        verify(productRepository).deleteById("P-1");
        verify(productRepository).existsById("P-1");
    }

}
