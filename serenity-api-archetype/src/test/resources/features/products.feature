Feature: Management a product

  @SearchProduct
  Scenario: Search a product limiting the results to 10 items.
    Given Carolina is using the API
    When she is looking for products
    Then she should see the product list


  @GetProduct
  Scenario: Get a simple product
    Given Roberto is using the API
    When he is looking for a product
    Then he should see a simple product


  @AddProduct
  Scenario: Add a new product
    Given Laura is using the API
    When she adds a new product
    Then she should see a product added


  @UpdateProduct
  Scenario: Update an existing product
    Given Carlos is using the API
    When he updates a product
    Then he should see a product updated


  @DeleteProduct
  Scenario: Delete an existing product
    Given Sofia is using the API
    When she deletes a product
    Then she should see the product deleted