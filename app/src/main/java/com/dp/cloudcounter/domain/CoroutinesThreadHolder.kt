package com.dp.cloudcounter.domain

import kotlinx.coroutines.experimental.newFixedThreadPoolContext

internal val Background = newFixedThreadPoolContext(2, "bg")