from django.http.response import HttpResponse
from .models import HeapUser
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from .serializers import HeapUserSerializer, HeapUserSafeSerializer

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
    
# class LoginView(APIview):
#     def post(self, request):

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
        details = HeapUserSafeSerializer(request.data)
        email = details['user_email']
        password = details['user_password']
        try:
            item = HeapUser.objects.get(user_email=email)
        except HeapUser.DoesNotExist:
            return Response({"status": "error", "data": "user does not exist"}, status=status.HTTP_400_BAD_REQUEST)
        
        if item.data['user_password'] != password:
            return Response({"status": "error", "data": "Wrong password"}, status=status.HTTP_400_BAD_REQUEST)
        
        serializer = HeapUserSafeSerializer(item.data)

        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)


        
        
        