package com.outdeved.alure.model.lure;

public enum Lure {

	SPINNER_BAIT("Spinner Bait",
			"Mimics small baitfish with metallic blades that spin like a propellor and create vibrations in the water.",
			"images/spinner.png",
			"Best for murky water or low light when vibration and flash help bass locate the lure."),
	SWIM_BAIT("Swim Bait",
			"Fish shaped baits that are designed to immitate realistic fish motions.",
			"images/swimbait.png",
			"Ideal for targeting larger, more cautious bass that feed on baitfish."),
	JERK_BAIT("Jerk Bait",
			"Minnow-shaped bait that has an erratic motion mimicking a wounded or dying fish.",
			"images/jerkbait.png",
			"Effective in cooler water when bass are sluggish but will respond to a dying baitfish."),
	HARD_TOPWATERS("Hard Topwater Baits",
			"Surface lures including poppers, spooks, and prop baits that create a commotion on the water's surface.",
			"images/popper.png",
			"Great for early morning or evening when bass are feeding near the surface."),
	CRANK_BAIT("Crank Bait",
			"Diving lures that shake, rattle, and have lips that dictate their diving depth.",
			"images/crankbait.png",
			"Perfect for covering water quickly and triggering reaction strikes."),
	SPOON("Spoon",
			"Metal lures that flash as they drop or during retrieval, appearing as a wounded baitfish.",
			"images/spoon.png",
			"Useful for vertical jigging or deep water fishing where flash attracts fish."),
	BUZZ_BAIT("Buzz Bait",
			"Surface lure that gurgles water as it moves across the surface, drawing strikes from its obnoxious commotion.",
			"images/buzzbait.png",
			"Triggers aggressive surface strikes, especially in warm, shallow water."),
	PLASTIC_WORM("Plastic Worm",
			"Versatile bait that can be fished throughout the water column, featuring a distinctive wiggle and sway.",
			"images/plasticworm.png",
			"Highly effective in a wide range of conditions and presentations, especially around cover."),
	CREATURE_BAIT("Creature Bait",
			"Mimics small animals that bass feed on including leeches, fish, lizards, and crayfish.",
			"images/creature.png",
			"Excellent for flipping and pitching into heavy cover where bass ambush prey."),
	GRUB("Grub",
			"Baits resembling squished worms, providing a subtle tail wiggling action.",
			"images/grub.png",
			"Great for finesse fishing when bass are finicky or pressured."),
	JIG("Jig",
			"Bait featuring a weighted head attached to a hook resembling small creatures with a weedless presentation.",
			"images/jig.png",
			"Ideal for bottom fishing in heavy cover and mimicking crawfish or baitfish."),
	TUBE("Tube",
			"Hollow bait with tentacle appendages creating a subtle action that resembles crayfish.",
			"images/tube.png",
			"Effective in clear water where subtle, realistic movements are needed."),
	FROG("Frog",
			"Hollow or soft bodied frog that create a ripple and splash on the water's surface as they are retrieved.",
			"images/frog.png",
			"Perfect for fishing over heavy vegetation and lily pads where other lures get snagged.");

	public final String name;
	public final String description;
	public final String imageFile;
	public final String reason;

	private Lure(String name, String description, String imageFile, String reason) {
		this.name = name;
		this.description = description;
		this.imageFile = imageFile;
		this.reason = reason;
	}

}
