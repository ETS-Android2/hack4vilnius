from rest_framework import serializers
from .models import HeapUser, PointsList, HeapOrganisation, PointsAdditions


class HeapUserSerializer(serializers.ModelSerializer):
    user_email = serializers.CharField(max_length=255)
    user_password = serializers.CharField(max_length=255)
    user_ID = serializers.IntegerField(required=False)
    user_points = serializers.IntegerField(required=False)

    class Meta:
        model = HeapUser
        fields = ('__all__')


class HeapOrganisationSerializer(serializers.ModelSerializer):
    organisation_email = serializers.CharField(max_length=255)
    organisation_password = serializers.CharField(max_length=255)
    organisation_ID = serializers.IntegerField(required=False)

    class Meta:
        model = HeapOrganisation
        fields = ('__all__')


class PointsListSerializer(serializers.ModelSerializer):
    points = serializers.IntegerField(required=False)
    name = serializers.CharField(max_length=64)
    descriptions = serializers.CharField(max_length=255)

    class Meta:
        model = PointsList
        fields = ('__all__')


class HeapUserSafeSerializer(serializers.ModelSerializer):
    user_email = serializers.CharField(max_length=255)
    user_ID = serializers.IntegerField(required=False)
    user_points = serializers.IntegerField(required=False)

    class Meta:
        model = HeapUser
        fields = ['user_email', 'user_ID', 'user_points']
    

class LocationsSerializer(serializers.ModelSerializer):
    name = serializers.CharField(max_length=64, required=True)
    latitude = serializers.FloatField(required=True)
    longitude = serializers.FloatField(required=True)
    location_address = serializers.CharField(max_length=255, required=True)


class PointsAdditionsSerializer(serializers.ModelSerializer):
    sender = serializers.PrimaryKeyRelatedField(many=False, read_only=True)
    recipient = serializers.PrimaryKeyRelatedField(many=False, read_only=True)
    points = serializers.IntegerField()
    date_sent = serializers.DateTimeField()

    class Meta:
        model = PointsAdditions
        fields = ('__all__')