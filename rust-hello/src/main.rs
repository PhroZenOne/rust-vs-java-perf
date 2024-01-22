use axum::{extract::Query, routing::get, Router, response::Json};
use rand::{thread_rng, Rng};
use serde::{Deserialize, Serialize};

#[tokio::main]
async fn main() {
    let app = Router::new().route("/", get(handler));

    axum::Server::bind(&"0.0.0.0:8080".parse().unwrap())
        .serve(app.into_make_service())
        .await
        .unwrap();
}

#[derive(Deserialize)]
struct RangeParameters {
    start: usize,
    end: usize,
}

#[derive(Serialize)]
struct Response {
    random_number: usize
}

async fn handler(Query(range): Query<RangeParameters>) -> Json<Response> {
    let random_number = thread_rng().gen_range(range.start..range.end);
    let r = Response {random_number};
    Json(r)
}