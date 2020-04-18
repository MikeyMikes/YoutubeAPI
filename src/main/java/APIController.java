import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;

public class APIController {

    public static void main(String[] args)
            throws GeneralSecurityException, IOException {

        YouTube youtubeService = AuthorizationService.getService();

        YoutubeChannelHelper.createComment(youtubeService, "IC4ZZRk-ZasGFabcD3iViAzh", "PSqwVsn0Cfx", "Test from API call");

        YoutubeChannelHelper.getPlaylistsByChannelId(youtubeService, "IC4ZZRk-ZasGFabcD3iViAzh");

        PlaylistListResponse testPlaylist = YoutubeChannelHelper.getPlaylistById(youtubeService, "OLaNwY5NYrSlXABAZ9NtASDh8bcOdABCD");
        YoutubeChannelHelper.deletePlaylistById(youtubeService, testPlaylist.getItems().get(0).getId());

        PlaylistFactory playlist1 = new PlaylistFactory(youtubeService, "en",
                "Testing this new API",
                "API Test",
                "private",
                new ArrayList<String>(
                    Arrays.asList("amazing", "crazy", "insane")));
        playlist1.createPlaylist(youtubeService);

        PlaylistFactory playlist2 = new PlaylistFactory(youtubeService);
        playlist2.setTitle("Wow, 2nd Test");
        playlist2.setDescription("Are you reading this right now?");
        playlist2.setTags(new ArrayList<String>(
                Arrays.asList("test", "wow", "cool")));
        playlist2.createPlaylist(youtubeService);

        playlist1.deletePlaylist(youtubeService);
    }

}
