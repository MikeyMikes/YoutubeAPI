import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;

import java.io.IOException;

public class YoutubeChannelHelper {

    public static PlaylistListResponse getPlaylistsByChannelId(YouTube youtubeService, String channelId) throws IOException {
        YouTube.Playlists.List request = youtubeService.playlists()
                .list("contentDetails,id,snippet").setChannelId(channelId);
        return request.execute();
    }

    public static PlaylistListResponse getPlaylistById(YouTube youtubeService, String id) throws IOException {
        YouTube.Playlists.List request = youtubeService.playlists()
                .list("contentDetails,id,snippet").setId(id);
        return request.execute();
    }

    public static void deletePlaylistById(YouTube youtubeService, String id) throws IOException {
        YouTube.Playlists.Delete request = youtubeService.playlists()
                .delete(id);
        request.execute();
    }

    public static void createComment(YouTube youtubeService, String channelId, String videoId, String text) throws IOException {
        CommentThread commentThread = new CommentThread();
        CommentThreadSnippet snippet = new CommentThreadSnippet();
        snippet.setChannelId(channelId);
        snippet.setVideoId(videoId);
        snippet.setTopLevelComment(new Comment().setSnippet(new CommentSnippet().setTextOriginal(text)));
        commentThread.setSnippet(snippet);

        YouTube.CommentThreads.Insert request = youtubeService.commentThreads()
                .insert("id,replies,snippet", commentThread);
        CommentThread response = request.execute();
    }

}
