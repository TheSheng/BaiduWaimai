var myApp = angular.module('menu', ['ngCookies'])

myApp.controller('menuController', ['$scope', '$http','$cookies','$cookieStore',function ($scope, $http,$cookies,$cookieStore) {
    var vm = $scope
    vm.flag=false

    //true登录，false  注册
    vm.rs=$cookieStore.get("user")
    if(null==vm.rs){

        swal("错误","请先登录","error")
        setTimeout(login,1000)


    }
    vm.edit=function(){
        window.location.href="/user"
    }
    vm.user=vm.rs['message']
    vm.loginout=function () {
        $cookieStore.remove("user")

        window.location.href="/login"

    }
    function login(){
        window.location.href="/login"
    }
    console.log(vm.user)


    request=$http
    vm.register=function () {
        data={
            username:vm.username,
            password:hex_md5(vm.password),
            imgUrl: vm.imgUrl

        }
        console.log(data)
        request({
            method: 'POST',
            url: 'http://localhost:9999/register',
            data: data



        }).then(function (response) {
            // console.log(response.data)
            vm.index="登录"
            vm.flag=false

            swal("OK!", "注册成功，请登录", "success");





        }, function (response) {

        });


    }
    function tiao(url){
        window.location.href=url
    }
    vm.login=function () {
        data = {
            username: vm.username,
            password: hex_md5(vm.password),

        }
        console.log(data)
        request({
            method: 'POST',
            url: 'http://localhost:9999/login',
            data: data


        }).then(function (response) {
            // console.log(response.data)
            var rs=response.data
            if(rs.code=="200"){
                $cookieStore.put("user",rs)

                tiao("/menu")
            }else{
                swal("错误!", "用户名或密码错误", "error");
            }
            // console.log(response)
            // response.data.


        }, function (response) {

        });
    }


}])