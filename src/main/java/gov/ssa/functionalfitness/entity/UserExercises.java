package gov.ssa.functionalfitness.entity;

import java.util.ArrayList;

public class UserExercises {
	private Object exerciseName = new ArrayList<>();
	private Object exerciseComment = new ArrayList<>();
	private Object muscleCategory = new ArrayList<>();
	private Object requiredEquipmemt = new ArrayList<>();
	
	public UserExercises() {
		super();
	}

	public UserExercises(Object exerciseName, Object exerciseComment, Object muscleCategory, Object requiredEquipmemt) {
		super();
		this.exerciseName = exerciseName;
		this.exerciseComment = exerciseComment;
		this.muscleCategory = muscleCategory;
		this.requiredEquipmemt = requiredEquipmemt;
	}

	public Object getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(Object exerciseName) {
		this.exerciseName = exerciseName;
	}

	public Object getExerciseComment() {
		return exerciseComment;
	}

	public void setExerciseComment(Object exerciseComment) {
		this.exerciseComment = exerciseComment;
	}

	public Object getMuscleCategory() {
		return muscleCategory;
	}

	public void setMuscleCategory(Object muscleCategory) {
		this.muscleCategory = muscleCategory;
	}

	public Object getRequiredEquipmemt() {
		return requiredEquipmemt;
	}

	public void setRequiredEquipmemt(Object requiredEquipmemt) {
		this.requiredEquipmemt = requiredEquipmemt;
	}

	@Override
	public String toString() {
		return "UserExercises [exerciseName=" + exerciseName + ", exerciseComment=" + exerciseComment
				+ ", muscleCategory=" + muscleCategory + ", requiredEquipmemt=" + requiredEquipmemt + "]";
	}
	
	 

	
}
