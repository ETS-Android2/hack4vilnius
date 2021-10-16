from django.db import models


class HeapUser(models.Model):
    user_email = models.CharField(max_length=255, unique=True)
    user_password = models.CharField(max_length=255)
    user_ID = models.IntegerField(primary_key=True, unique=True)
    user_points = models.IntegerField(default=0)


class HeapOrganisation(models.Model):
    organisation_email = models.CharField(max_length=255)
    organisation_password = models.CharField(max_length=255)
    organisation_ID = models.IntegerField(primary_key=True, unique=True)


class PointsList(models.Model):
    points = models.IntegerField()
    name = models.CharField(max_length=64)
    descriptions = models.CharField(max_length=255)


class Locations(models.Model):
    name = models.CharField(max_length=64)
    latitude = models.FloatField()
    longitude = models.FloatField()
    location_address = models.CharField(max_length=255)

class PointsAdditions(models.Model):
    sender = models.ForeignKey(HeapOrganisation, on_delete=models.CASCADE)
    recipient = models.ForeignKey(HeapUser, on_delete=models.CASCADE)
    points = models.IntegerField()
    date_sent = models.DateTimeField(auto_now=True)

