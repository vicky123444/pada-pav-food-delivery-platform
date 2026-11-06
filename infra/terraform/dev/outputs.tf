output "rds_endpoint" {
  value = aws_db_instance.postgres.address
}

output "opensearch_endpoint" {
  value = aws_opensearch_domain.search.endpoint
}

output "s3_bucket_name" {
  value = aws_s3_bucket.media.bucket
}

output "eks_cluster_name" {
  value = aws_eks_cluster.main.name
}

output "sqs_queue_url" {
  value = aws_sqs_queue.order_events.url
}
