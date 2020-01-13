package com.algorithm.main;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.algorithm.exception.NegativeSkillSetException;
import com.algorithm.model.Player;
import com.algorithm.service.AlgorithmApplicationService;

@SpringBootApplication
public class AlgorithmApplication implements AlgorithmApplicationService {

	public List<Player> takingInputFromUser() throws NegativeSkillSetException {
		List<Player> skillNumberList = null;
		System.out.println("How many Player in a Football Field : ");
		Scanner inputScanner = new Scanner(System.in);
		int playerNumber = inputScanner.nextInt();
		if (playerNumber < 2) {
			System.out.println("HA HA... You can shoot Yourself..");
		} else {
			skillNumberList = new LinkedList<>();
			System.out.println("Please Provide the Skill No ");
			for (int i = 0; i < playerNumber; i++) {
				int inputSkillValue = inputScanner.nextInt();
				if (inputSkillValue < 0) {
					throw new NegativeSkillSetException("You Can not set a skill number with (-) negative value..");
				} else {
					skillNumberList.add(new Player(inputSkillValue, false));
				}
			}
		}
		return skillNumberList;
	}

	public int remainingPlayerSkillSum(List<Player> skillNumberList) {
		int sum = (int) skillNumberList.stream().mapToInt((i) -> i.getSkill()).summaryStatistics().getSum();
		return sum;
	}

	public static void main(String[] args) throws NegativeSkillSetException {
		SpringApplication.run(AlgorithmApplication.class, args);
		AlgorithmApplicationService service = new AlgorithmApplication();
		List<Player> skillNumberList = service.takingInputFromUser();
		if (skillNumberList != null) {
			skillNumberList.sort((Player p1, Player p2) -> p1.getSkill() - p2.getSkill());
			for (int i = 0, j = 1; i < skillNumberList.size() && j < skillNumberList.size();) {
				if (skillNumberList.get(i).getSkill().equals(skillNumberList.get(j).getSkill())) {
					System.out.println("Both have Same Skill");
					j++;
				} else if (skillNumberList.get(i).getSkill() < skillNumberList.get(j).getSkill()) {
					if ((skillNumberList.get(i).isShoted() == false && skillNumberList.get(j).isShoted() == false)) {
						System.out.println(
								skillNumberList.get(j).getSkill() + " killed " + skillNumberList.get(i).getSkill());
						skillNumberList.get(j).setShoted(true);
						skillNumberList.remove(skillNumberList.get(i));
					} else if (skillNumberList.get(i).isShoted() == true
							&& skillNumberList.get(j).isShoted() == false) {
						System.out.println(
								skillNumberList.get(j).getSkill() + " killed " + skillNumberList.get(i).getSkill());
						skillNumberList.get(j).setShoted(true);
						skillNumberList.remove(skillNumberList.get(i));
					} else if (skillNumberList.get(i).isShoted() == false
							&& skillNumberList.get(j).isShoted() == true) {
						j++;
					} else if (skillNumberList.get(i).isShoted() == true && skillNumberList.get(j).isShoted() == true) {
						j++;
					}
				}
			}
			System.out.println("OutPuted Skill number are : " + skillNumberList);
			System.out
					.println("Remaining player's skill sum is :: " + service.remainingPlayerSkillSum(skillNumberList));
		} else {
			service.takingInputFromUser();
		}
	}
}