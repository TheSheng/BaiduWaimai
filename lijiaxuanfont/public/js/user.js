var myApp = angular.module('login', ['ngCookies'])

myApp.controller('loginController', ['$scope', '$http','$cookies','$cookieStore',function ($scope, $http,$cookies,$cookieStore) {
    var vm = $scope
    vm.rs=$cookieStore.get("user")
    if(null==vm.rs){
        swal("错误","请先登录再修改","error")
         setTimeout(login,2000)
    }
    vm.user=vm.rs['message']
    // vm.old=vm.user['password']
    vm.gai=function () {
        if(vm.user.password.length<6||vm.user.password.length>15){
            swal("错误","请重新定义密码","error")
           return
        }
        console.log(vm.user.password)
        data={
            id:vm.user.id,
            username:vm.user.username,
            password:hex_md5(vm.user.password),
            imgUrl: vm.user.imgUrl

        }
        console.log(data)
        $http({
            method: 'POST',
            url: 'http://localhost:9999/user',
            data: data



        }).then(function (response) {
            console.log(response.data)




            swal("OK!", "修改成功，请登录", "success")
            $cookieStore.remove("user")
           setTimeout(login,1000)




        }, function (response) {

        });


    }
    function login() {
        window.location.href="/login"
    }


}])