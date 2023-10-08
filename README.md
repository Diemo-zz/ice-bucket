# Build
- Copy the .env.example file to .env

- Run docker compose -f docker-compose.yml up

This will create a service that you can query on port <PORT> (default 80)


# Endpoints
- add artist: /artist/add
- add track: /track/add
- daily artist: /artist/daily
- edit artist name: /artist/edit?artistId=<>&newName=<>
- get all tracks: /tracks?artistId=<id>

## Add artist
Expects an artist as a JSON input - e.g.

```json
{
  "name": "<artistName>",
  "aliases": ["<alias1>", "<alias2>"]
}
```

## Add track
The artist must already exist. Expects JSON in the form
```json
{
  "title": "<track_title>",
  "artistId": "<artist_id>"
}
```
where artist id is the ID given when calling add artist