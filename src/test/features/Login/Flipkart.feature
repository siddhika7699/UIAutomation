Feature: Search and Add Product in to the Filpkart Application

  @AddProduct
  Scenario: "Verify that the user is able to search for a product and successfully add it to the cart on the Flipkart application.
    Given User open the url of Flipkart Application
    When User searches for "Apple iPhone 16 Pro" in the search bar.
    And User adds "Apple iPhone 16 Pro" in the Cart
    Then User verify that "Apple iPhone 16 Pro" product added in the cart