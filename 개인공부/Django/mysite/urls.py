"""mysite URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.conf.urls import include, url
from django.contrib import admin
from django.urls import (path, include)
from allauth.account import views
from django.contrib.auth.views import LoginView,LogoutView
from blog.views import SocialLoginCallbackView

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    path('user/', include('blog.urls')),
    path('accounts/', include('django.contrib.auth.urls')),
    path('accounts/',include('allauth.urls')),
    
    url(r"^accounts/password/reset/$", views.password_reset,name="account_reset_password"),
    url(r"^password/reset/done/$", views.password_reset_done,name="account_reset_password_done"),
    url(r"^password/reset/key/(?P<uidb36>[0-9A-Za-z]+)-(?P<key>.+)/$",views.password_reset_from_key,name="account_reset_password_from_key"),
    url(r"^password/reset/key/done/$", views.password_reset_from_key_done,name="account_reset_password_from_key_done"),
    
    url(r'^accounts/login/$',  views.login, name='login'),
    url(r'^accounts/logout/$', views.logout, name='logout', kwargs={'next_page': '/'}),
    path('accounts/login/<provider>/callback/', SocialLoginCallbackView.as_view()),
    url(r'', include('blog.urls')),
    
    
]
