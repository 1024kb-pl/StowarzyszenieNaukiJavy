var app = angular.module('app', ['ngResource']);

app.controller("ProductController", function($http, $resource){
    var vm = this;
    var Product = $resource('api/products/:productId');
    vm.product = new Product();

    function refreshData(){
        /*$http.get("api/products")
        .then(function success(response){
            vm.products = response.data
            console.log(vm.products)
        },function error(response){
            console.log("API erro " + response.status)
        })*/
        vm.products = Product.query(
            function success(data, headers){
                console.log('Pobrano dane' + data);
                console.log(headers('Content-Type'));
            },
            function error(response){
                console.log("error" + response.status);
            });
    }

    vm.addProduct = function(product){
        /*$http.post("api/products", product)
        .then(function success(response){
            refreshData()
            vm.product = {}
        }, function error(response){
            console.log("Data not saved " + product)
        })*/
        console.log("adding" + vm.product.__proto__);
        vm.product.$save(function(data){
            refreshData();
            vm.product = new Product();
        })
    }

    vm.loadDetails = function(id){
        vm.details = Product.get({productId:id});
    }

    vm.appName = "Product Manager";
    refreshData();
});

app.controller("HomeController", function ($resource) {

    this.appTitle = "Simple"
})

app.controller("LoginController", function ($http, $resource) {

    this.signIn = "Sign In"
})