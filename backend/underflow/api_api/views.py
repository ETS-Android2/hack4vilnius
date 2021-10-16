from django.http.response import HttpResponse
from .models import HeapUser, Locations
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from .serializers import HeapUserSerializer, HeapUserSafeSerializer, LocationsSerializer

# Create your views here.

class HeapUserViews(APIView):
    def post(self, request):
        serializer = HeapUserSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        else:
            return Response({"status": "error", "errors": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)
    
    def get(self, request):
        return Response({"status":"Success"})
    

class GetUserInfo(APIView):
    def get(self, request, id=None):
        if id:
            try:
                item = HeapUser.objects.get(user_ID=id)
            except HeapUser.DoesNotExist:
                return Response({"status": "error", "data": "bad id"}, status=status.HTTP_400_BAD_REQUEST)

            print(item)
            serializer = HeapUserSafeSerializer(item)
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        
        return Response({"status": "error", "data": "missing id"}, status=status.HTTP_400_BAD_REQUEST)

        
class GetUsersInfo(APIView):
    def get(self, request, id=None):
        items = HeapUser.objects.all()
        serializer = HeapUserSafeSerializer(items, many=True)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)


class LoginView(APIView):
    def post(self, request):
        details = HeapUserSerializer(data=request.data)
        print(details.is_valid())
        email = details.validated_data['user_email']
        password = details.validated_data['user_password']
        try:
            item = HeapUser.objects.get(user_email=email)
        except HeapUser.DoesNotExist:
            return Response({"status": "error", "data": "user does not exist"}, status=status.HTTP_400_BAD_REQUEST)
        
        if item['user_password'] != password:
            return Response({"status": "error", "data": "Wrong password"}, status=status.HTTP_400_BAD_REQUEST)
        
        serializer = HeapUserSafeSerializer(item.data)

        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)


class LocationsView(APIView):
    def get(self, request):
        locations = Locations.objects.all()
        serializer = LocationsSerializer(locations, many=True)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)


        
        