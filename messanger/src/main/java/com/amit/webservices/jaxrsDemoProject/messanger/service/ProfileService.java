package com.amit.webservices.jaxrsDemoProject.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amit.webservices.jaxrsDemoProject.messanger.database.DatabaseClass;
import com.amit.webservices.jaxrsDemoProject.messanger.model.Profile;

public class ProfileService {
	
	private  Map<String, Profile> profiles = DatabaseClass.getProfile();
	
	public ProfileService() {
		profiles.put("Amit", new Profile(1L, "AmitSahu", "Amit", "Sahu"));
		profiles.put("Neha", new Profile(2L, "NehaGanjir", "Neha", "Ganjir"));
	}

	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
		
	}
}
