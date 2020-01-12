package com.algorithm.service;

import java.util.List;

import com.algorithm.exception.NegativeSkillSetException;
import com.algorithm.model.Player;

public interface AlgorithmApplicationService {

	public List<Player> takingInputFromUser() throws NegativeSkillSetException;

	public int remainingPlayerSkillSum(List<Player> skillNumberList);
}
