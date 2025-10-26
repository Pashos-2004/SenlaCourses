package subtask_3;

interface ProductPart {
    String getName();
}

interface LineStep {
    ProductPart buildProductPart();
}

interface Product {
    void installFirstPart(ProductPart part);
    void installSecondPart(ProductPart part);
    void installThirdPart(ProductPart part);
    void displayInfo();
}

interface AssemblyLine {
    Product assembleProduct(Product product);
}