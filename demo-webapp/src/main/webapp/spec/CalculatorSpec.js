describe("Calculator", function() {
    var calculator = null;

    beforeEach(function() {
        calculator = new Calculator();
    });

    it("should sum of two numbers", function() {
        expect(calculator.sum(2, 4)).toEqual(6);
    });
});