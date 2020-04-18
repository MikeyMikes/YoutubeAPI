import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistSnippet;
import com.google.api.services.youtube.model.PlaylistStatus;

import java.io.IOException;
import java.util.List;

public class PlaylistFactory {

    private Playlist playlist;

    public PlaylistFactory(YouTube youtubeService) {
        this.playlist = new Playlist();
        PlaylistSnippet snippet = new PlaylistSnippet();
        playlist.setSnippet(snippet);
    }

    public PlaylistFactory(YouTube youtubeService, String language, String description,
                           String title, String privacyStatus, List<String> tags) {
        this.playlist = new Playlist();

        PlaylistSnippet snippet = new PlaylistSnippet();
        snippet.setDefaultLanguage(language);
        snippet.setDescription(description);
        snippet.setTags(tags);
        snippet.setTitle(title);
        playlist.setSnippet(snippet);

        PlaylistStatus status = new PlaylistStatus();
        status.setPrivacyStatus(privacyStatus);
        playlist.setStatus(status);
    }

    public void createPlaylist(YouTube youtubeService) throws IOException {
        YouTube.Playlists.Insert request = youtubeService.playlists()
                .insert("snippet,status", playlist);
        Playlist response = request.execute();
        System.out.println(response);
    }

    public void deletePlaylist(YouTube youtubeService) throws IOException {
        YouTube.Playlists.Delete request = youtubeService.playlists()
                .delete(playlist.getId());
        request.execute();
    }

    public String getLanguage() {
        return this.playlist.getSnippet().getDefaultLanguage();
    }

    public void setLanguage(String language) {
        this.playlist.getSnippet().setDefaultLanguage(language);
    }

    public String getDescription() {
        return this.playlist.getSnippet().getDescription();
    }

    public void setDescription(String description) {
        this.playlist.getSnippet().setDescription(description);
    }

    public String getTitle() {
        return this.playlist.getSnippet().getTitle();
    }

    public void setTitle(String title) {
        this.playlist.getSnippet().setTitle(title);
    }

    public String getPrivacyStatus() {
        return this.playlist.getStatus().getPrivacyStatus();
    }

    public void setPrivacyStatus(String privacyStatus) {
        this.playlist.getStatus().setPrivacyStatus(privacyStatus);
    }

    public List<String> getTags() {
        return this.playlist.getSnippet().getTags();
    }

    public void setTags(List<String> tags) {
        this.playlist.getSnippet().setTags(tags);
    }
}
