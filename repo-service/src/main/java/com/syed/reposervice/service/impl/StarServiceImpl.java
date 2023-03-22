package com.syed.reposervice.service.impl;

import com.syed.reposervice.dto.StarredRepoDto;
import com.syed.reposervice.entity.Repo;
import com.syed.reposervice.entity.StarredRepo;
import com.syed.reposervice.exception.NotFoundException;
import com.syed.reposervice.repository.RepoRepository;
import com.syed.reposervice.repository.StarRepository;
import com.syed.reposervice.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl implements StarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StarServiceImpl.class);

    private final StarRepository starRepository;
    private final RepoRepository repoRepository;

    public StarServiceImpl(StarRepository starRepository, RepoRepository repoRepository) {
        this.starRepository = starRepository;
        this.repoRepository = repoRepository;
    }

    /**
     * method which stars and un stars a repo
     * @param starredRepoDto the repo to star along with by which user
     * @return a message which states where a repo has been starred or un starred
     */
    @Override
    public String starRepo(StarredRepoDto starredRepoDto) {
        LOGGER.info("Entering StarServiceImpl:starRepo");

        // check if repo exists
        Repo repo = repoRepository.findById(starredRepoDto.getRepoId()).orElseThrow(
                () -> new NotFoundException("Repository with id " + starredRepoDto.getRepoId() + " not found.")
        );

        // check if repo has already been starred
        boolean exists = starRepository.existsByRepoIdAndStarredBy(starredRepoDto.getRepoId(), starredRepoDto.getStarredBy());

        if (!exists) {
            LOGGER.info("Repo not starred yet - need to star");
            StarredRepo starredRepo = new StarredRepo();
            starredRepo.setRepo(repo);
            starredRepo.setStarredBy(starredRepoDto.getStarredBy());

            starRepository.save(starredRepo);

            return "Repo added to favourites";
        } else {
            LOGGER.info("Repo already starred - need to unstar");
            starRepository.deleteByRepoIdAndStarredBy(
                    starredRepoDto.getRepoId(),starredRepoDto.getStarredBy());

            return "Repo removed from favourites";
        }
    }
}
