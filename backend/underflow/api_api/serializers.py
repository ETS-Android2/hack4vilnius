from rest_framework import serializers
from .models import HeapUser, PointsList


class HeapUSerSerializer(serializers.ModelSerializer):
    user_email = serializers.CharField(max_length=255)
    user_password = serializers.CharField(max_length=255)
    user_ID = serializers.IntegerField(required=False)
    user_points = serializers.IntegerField(required=False)

    class Meta:
        model = HeapUser
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
    
