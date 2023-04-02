package com.syed.reposervice.utility;

import com.syed.reposervice.dto.RepoDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepoServiceUtility {

    /**
     * method which converts a list of repos to a map
     * map contains category as a key and value is a list of repos
     * @param starredRepos the list of starred repos
     * @return map of category
     */
    public static Map<String, List<RepoDto>> getCategoryRepoMap(List<RepoDto> starredRepos) {
        Map<String, List<RepoDto>> map = new HashMap<>();
        String tempKey;

        for (RepoDto repoDto : starredRepos) {
            tempKey = repoDto.getCategory().toString();

            if (!map.containsKey(tempKey)) {
                map.put(tempKey, new ArrayList<>());
            }

            map.get(tempKey).add(repoDto);
        }

        return map;
    }
}
