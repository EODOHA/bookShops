<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"    
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>찾아오시는길</title>
    <style>
        #map {
            height: 500px;
            width: 100%;
        }
    </style>
</head>
<body>
    <h1>찾아오시는길</h1>
    
    <div id="map"></div>

    <script>

        var mapOptions = {
            center: new naver.maps.LatLng(37.570377, 126.992064), // 종로3가 좌표
            zoom: 15 // 줌 레벨 설정
        };

        var map = new naver.maps.Map('map', mapOptions);

        // 마커 추가
        var marker = new naver.maps.Marker({
            position: new naver.maps.LatLng(37.570377, 126.992064),
            map: map
        });
    </script>

   
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=NAVER_API_KEY"></script>
</body>
</html>
