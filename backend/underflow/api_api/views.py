from django.http.response import HttpResponse
from .models import HeapOrganisation, HeapUser, Locations, PointsAdditions
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from .serializers import HeapUserSerializer, HeapUserSafeSerializer, LocationsSerializer, HeapOrganisationSerializer, PointsAdditionsSerializer

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


class HeapOrganisationViews(APIView):
    def post(self, request):
        serializer = HeapOrganisationSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        else:
            return Response({"status": "error", "errors": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

class HeapOrganisationsView(APIView):
    def get(self, request):
        items = HeapOrganisation.objects.all()
        serializer = HeapOrganisationSerializer(items, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)


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
    def get(self, request):
        items = HeapUser.objects.all()
        serializer = HeapUserSafeSerializer(items, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)


class LoginView(APIView):
    def post(self, request):
        details = HeapUserSerializer(data=request.data)
        if not details.is_valid():
            return Response({"status": "error", "data": "missing id"}, status=status.HTTP_400_BAD_REQUEST)

        email = details.validated_data['user_email']
        password = details.validated_data['user_password']
        try:
            item = HeapUser.objects.get(user_email=email)
        except HeapUser.DoesNotExist:
            return Response({"status": "error", "data": "user does not exist"}, status=status.HTTP_400_BAD_REQUEST)
        if getattr(item, 'user_password') != password:
            return Response({"status": "error", "data": "Wrong password"}, status=status.HTTP_400_BAD_REQUEST)
        
        serializer = HeapUserSafeSerializer(item)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)


class LocationsView(APIView):
    def get(self, request):
        locations = Locations.objects.all()
        serializer = LocationsSerializer(locations, many=True)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)


class PointsAdditionsView(APIView):
    def get(self, request, receiver_id=None):
        if receiver_id:
            try:
                points = PointsAdditions.objects.filter(recipient=receiver_id)
                serializer = PointsAdditionsSerializer(points, many=True)
            except PointsAdditions.DoesNotExist:
                return Response({"status": "error", "data": "recipient does not exist"}, status=status.HTTP_400_BAD_REQUEST)
        else:
            points_list = PointsAdditions.objects.all()
            serializer = PointsAdditionsSerializer(points_list, many=True)

        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)

    # def post(self, request):
    #     serializer = PointsAdditionsSerializer(data=request.data)
    #     if serializer.is_valid():
    #         serializer.save()
    #         recipient = serializer.data['recipient']
    #         points = serializer.data['points']
    #         user = HeapUser.objects.get(user_ID=recipient)
    #         currentPoints = user.user_points
    #         user.user_points = currentPoints + points
    #         user.save()
    #         ss = PointsAdditionsSerializer(user)
    #         return Response({"status": "success", "data": ss.data}, status=status.HTTP_200_OK)
    #     else:
    #         return Response({"status": "error", "errors": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)